package com;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 这个例子在eclipse里不能直接编译，要到项目的属性， 
 * Java Compiler，Errors/Warnings中Deprecated and restricted API 
 * 中Forbidden reference(access rules)中设置为warning。 
 *
 * 获取一个Java对象在内存所占的空间，不同的虚拟机内存管理方式可能不同， 
 * 本例是针对32位的hotspot虚拟机的。 
 *
 * 由于虚拟机对字符串做了特殊处理，比如将其放入常量池，因此sizeof得到的字符串 
 * 包含了常量池里面占用的空间。基本类型的包装类也会重复利用对象。 
 *
 * 设计作者: teasp 
 * 信息描述： 
 */
@SuppressWarnings("restriction")
public class HotspotSizeof
{
    public static final int OBJ_BASIC_LEN = 8 * 8;
    public static final int ARRAY_BASIC_LEN = 12 * 8;
    public static final int OBJ_REF_LEN = 4 * 8;

    public static final int ALIGN = 8 * 8;

    private static Unsafe UNSAFE;

    static {
        try
        {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 原始类型的种类，以及每个类型所占空间，单位为bit 
     * @author Administrator
     *
     */
    private enum PType
    {
        布尔(8)/*Java语言规定是1个bit*/,字节(8),字符(16),短整(16),
        整形(32),浮点(32),长整(64),双精(64);

        private int bits;

        private PType(int bits)
        {
            this.bits = bits;
        }

        public int getBits() {
            return bits;
        }
    }

    /**
     * 计算obj对象在虚拟机中所占的内存，单位为bit。 
     * 如果isPapa为true，则表明计算的是obj对象父类定义的属性。 
     *
     * @param obj
     * @param clazz
     * @param isPapa
     * @return
     */
    private static int getObjBits(Object obj, Class<?> clazz, boolean isPapa)
    {
        int bits = 0;
        if (obj == null)
        {
            return bits;
        }

        bits += OBJ_BASIC_LEN;
        if (isPapa)
        {
            bits = 0;
        }

        Field[] fields = clazz.getDeclaredFields();
        if (fields != null)
        {
            for (Field field : fields)
            {
                //静态属性不参与计算  
                if (Modifier.isStatic(field.getModifiers()))
                {
//                    System.out.println("static " + field.getName());  
                    continue;
                }
                Class<?> c = field.getType();
                if (c == boolean.class)
                {
                    bits += PType.布尔.getBits();
                }
                else if (c == byte.class)
                {
                    bits += PType.字节.getBits();
                }
                else if (c == char.class)
                {
                    bits += PType.字符.getBits();
                }
                else if (c == short.class)
                {
                    bits += PType.短整.getBits();
                }
                else if (c == int.class)
                {
                    bits += PType.整形.getBits();
                    System.out.println(field.getName() + "=" + UNSAFE.getInt(obj, UNSAFE.objectFieldOffset(field)));
                }
                else if (c == float.class)
                {
                    bits += PType.浮点.getBits();
                }
                else if (c == long.class)
                {
                    bits += PType.长整.getBits();
                }
                else if (c == double.class)
                {
                    bits += PType.双精.getBits();
                }
                else if (c == void.class)
                {
                    //nothing
                } else if (c.isArray())
                {//是数组
                    Object o = UNSAFE.getObject(obj, UNSAFE.objectFieldOffset(field));
                    bits += OBJ_REF_LEN;
                    if (o != null)
                    {
                        try
                        {
                            bits += bitsofArray(o);
                        } catch (Exception e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                } else
                {//普通对象
                    Object o = UNSAFE.getObject(obj, UNSAFE.objectFieldOffset(field));
                    bits += OBJ_REF_LEN;
                    if (o != null)
                    {
                        try
                        {
                            bits += bitsof(o);
                        } catch (Exception e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        Class<?> papa = clazz.getSuperclass();
        if (papa != null)
        {
            bits += getObjBits(obj, papa, true);
        }

        //补齐，当计算父类属性时，因为是对同一个对象在进行统计，所以不要补齐。  
        //一个对象只做一次补齐动作。  
        if (false == isPapa)
        {
            if (bits%ALIGN > 0)
            {
                bits += (ALIGN - bits%ALIGN);
            }
        }

        return bits;
    }

    /**
     * 计算arr数组在虚拟机中所占的内存，单位为bit 
     * @param arr
     * @return
     */
    private static int bitsofArray(Object arr)
    {
        int bits = 0;
        if (arr == null)
        {
            return bits;
        }

        bits += ARRAY_BASIC_LEN;

        Class<?> c = arr.getClass();
        if (c.isArray() == false)
        {
            throw new RuntimeException("Must be array!");
        }

        if (c == boolean[].class)
        {
            bits += PType.布尔.getBits() * ((boolean[])arr).length;
        }
        else if (c == byte[].class)
        {
            bits += PType.字节.getBits() * ((byte[])arr).length;
        }
        else if (c == char[].class)
        {
            bits += PType.字符.getBits() * ((char[])arr).length;
        }
        else if (c == short[].class)
        {
            bits += PType.短整.getBits() * ((short[])arr).length;
        }
        else if (c == int[].class)
        {
            bits += PType.整形.getBits() * ((int[])arr).length;
        }
        else if (c == float[].class)
        {
            bits += PType.浮点.getBits() * ((float[])arr).length;
        }
        else if (c == long[].class)
        {
            bits += PType.长整.getBits() * ((long[])arr).length;
        }
        else if (c == double[].class)
        {
            bits += PType.双精.getBits() * ((double[])arr).length;
        }
        else
        {
            Object[] os = (Object[])arr;
            for (Object o : os)
            {
                bits += OBJ_REF_LEN + bitsof(o);
            }
        }

        //补齐  
        if (bits%ALIGN > 0)
        {
            bits += (ALIGN - bits%ALIGN);
        }

        return bits;
    }

    /**
     * 计算obj对象在虚拟机中所占的内存，单位为bit 
     * @param obj
     * @return
     */
    private static int bitsof(Object obj)
    {
        if (obj == null)
        {
            return 0;
        }

        if (obj.getClass().isArray())
        {
            return bitsofArray(obj);
        }

        return getObjBits(obj, obj.getClass(), false);
    }

    /**
     * 计算obj对象在虚拟机中所占的内存，单位为byte 
     * @param obj
     * @return
     */
    public static int sizeof(Object obj)
    {
        return bitsof(obj)/8;
    }

    private static void runGC() throws Exception
    {
        // It helps to call Runtime.gc()  
        // using several method calls:  
        for (int r=0; r<4; ++r) _runGC();
    }

    private static void _runGC() throws Exception
    {
        long usedMem1 = usedMemory(), usedMem2 = Long.MAX_VALUE;
        for (int i=0; (usedMem1<usedMem2) && (i<500); ++i)
        {
            Runtime.getRuntime().runFinalization();
            Runtime.getRuntime().gc();
            Thread.yield();

            usedMem2 = usedMem1;
            usedMem1 = usedMemory();
        }
    }

    private static long usedMemory()
    {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    /**
     * 本方法在计算String以及原始类型的包装类的时候可能不准。 
     * String s = "abc"; 这种方式产生的String对象会被放入常量池。 
     * Integer.valueOf(1)会返回缓存的对象而不是new一个。 
     * @param cls
     * @return
     * @throws Exception
     */
    private static long determinObjSize(Class<?> cls) throws Exception
    {
        // Warm up all classes/methods we will use  
        runGC();
        usedMemory();
        // Array to keep strong references to allocated objects  
        final int count = 100000;
        Object[] objects = new Object[count];

        long heap1 = 0;
        // Allocate count+1 objects, discard the first one  
        for (int i = -1; i < count; ++i)
        {
            Object object = null;

            // Instantiate your data here and assign it to object  

//            object = new Object();  
            //object = new Integer(i);  
            //object = new Long(i);  
            //object = new String();  
            //object = new byte[128][1]  
            object = cls.newInstance();

            if (i >= 0)
                objects [i] = object;
            else
            {
                object = null; // Discard the warm up object  
                runGC();
                heap1 = usedMemory(); // Take a before heap snapshot  
            }
        }
        runGC();
        long heap2 = usedMemory(); // Take an after heap snapshot:  

        final int size = Math.round(((float)(heap2 - heap1))/count);
        System.out.println("'before' heap: " + heap1 +
                ", 'after' heap: " + heap2);
        System.out.println("heap delta: " + (heap2 - heap1) +
                ", {" + objects [0].getClass () + "} size = " + size + " bytes");
        for (int i = 0; i < count; ++i) objects [i] = null;
        objects = null;

        return size;
    }

    public static void main(String[] args)
    {
        HotspotSizeof hs = new HotspotSizeof();
        hs.test();
    }

    @Test
    public void test()
    {
        try
        {
            Assert.assertEquals(determinObjSize(Obj4SizeofTest.class), sizeof(new Obj4SizeofTest()));
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}