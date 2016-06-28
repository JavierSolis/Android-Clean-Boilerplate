package com.kodelabs.boilerplate.domain.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 21/06/16.
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 * Company @PineappleLab
 * Contact hellopineapplelab@gmail.com
 *
 *
 * Clase que ayuda a mapear un entidad a otra,
 * copiara los valores si los atributos tiene el mismo nombre
 * los que no coincidan los ignorara
 *
 *
 * Example:
 *
 *
 public class ClassA
 {
 public String campo1;
 public double campo2;
 public int campo3;
 public Date campo4;
 public String campoA5;
 }



 public class ClassB
 {
 public String campo1;
 public double campo2;
 public int campo3;
 public Date campo4;
 public String campoB5;
 }


 Mapper:

 @Mapping( "campoA5:campoB5" )
 public class ClassAMapper extends BaseMapper<ClassA, ClassB>
 {

 @Override
 public ClassB map(ClassA objA) throws Exception {
 return super.map(objA);
 }
 }


 */


public abstract class BaseMapper<A, B> {

    private Class<A> typeOfA;
    private Class<B> typeOfB;

    private HashMap<String, String> mapFieldsAB;
    private HashMap<String, String> mapFieldsBA;

    public BaseMapper() {
        this.typeOfB = (Class<B>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[1];

        this.typeOfA = (Class<A>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];


        mapFieldsAB = new HashMap<>();
        mapFieldsBA = new HashMap<>();
        Mapping mapping = this.getClass().getAnnotation(Mapping.class);

        if (mapping != null) {
            String[] fields = mapping.value();
            for (String field : fields) {
                String[] mapField = field.split(":");
                mapFieldsAB.put(mapField[0], mapField[1]);
                mapFieldsBA.put(mapField[1], mapField[0]);

            }
        }


    }


    public B map(A objA) throws Exception {
        return (B) mapBase(typeOfA, typeOfB, objA, mapFieldsAB);
    }

    public A inverseMap(B objB) throws Exception {
        return (A) mapBase(typeOfB, typeOfA, objB, mapFieldsBA);
    }


    public List<B> mapLis(List<A> listObjA) throws Exception {
        return (List<B>) mapListBase(typeOfA, typeOfB, (List<Object>)listObjA, mapFieldsAB);
    }


    public List<A> inverseMapLis(List<B> listObjB) throws Exception
    {
        return (List<A>) mapListBase(typeOfB, typeOfA, (List<Object>) listObjB, mapFieldsBA);
    }



    private List<Object> mapListBase(Class classA, Class classB, List<Object> listObjectA, HashMap<String, String> mapFieldAB) throws Exception {
        List listObjB = new ArrayList<>();
        for (Object itemObjA:listObjectA) {
            listObjB.add(mapBase(classA, classB, itemObjA, mapFieldAB));
        }
        return listObjB;
    }



    private Object mapBase(Class classA, Class classB, Object objectA, HashMap<String, String> mapFieldAB) throws Exception {
        Constructor<?> ctor = classB.getConstructor();
        Object objectInstanceB = ctor.newInstance(new Object[]{});

        Field[] objAFields = classA.getDeclaredFields();

        for (Field fieldA : objAFields) {
            if (fieldA.isSynthetic()) {
                continue;
            }

            //por defecto son los mismos
            String fieldInBName = fieldA.getName();
            //si existe algun mapeo explicito se sobrescribe
            if (mapFieldAB.containsKey(fieldInBName)) {
                fieldInBName = mapFieldAB.get(fieldInBName);
            }

            boolean accesible = fieldA.isAccessible();
            fieldA.setAccessible(true);

            Object valueInA = fieldA.get(objectA);

            try {
                Field[] fieldsss = classB.getFields();
                Field[] fieldss = classB.getDeclaredFields();

                Field fieldInB = classB.getDeclaredField(fieldInBName);
                fieldInB.set(objectInstanceB, valueInA);
            } catch (NoSuchFieldException noSuchException) {
                noSuchException.printStackTrace();
                continue;
            }


            fieldA.setAccessible(accesible);
        }


        return objectInstanceB;
    }


}