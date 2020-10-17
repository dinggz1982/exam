package gzhu.edu.cn;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-10-14 23:21
 */
public class JenaTest {

    public static void main(String[] args) {
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        try {
            ontModel.read(new FileInputStream("D:/owl/1.owl"), "");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        // 迭代显示模型中的类，在迭代过程中完成各种操作
        ExtendedIterator<OntClass> classes = ontModel.listHierarchyRootClasses();
        while(classes.hasNext()){
            //System.out.println(classes.next().getLocalName());
            OntClass ontClass =  classes.next();
            System.out.println(ontClass.getLocalName());
            listSubClass(ontClass,1);
        }
    }

    public static void listSubClass(OntClass ontClass,int i){
        ExtendedIterator<OntClass> classes = ontClass.listSubClasses();
        while(classes.hasNext()){

            OntClass subOntClass =  classes.next();
            int j=i;
            while(j>0){
                System.out.print("-");
                j--;
            }
            System.out.println(subOntClass.getLocalName());
            listSubClass(subOntClass,i+1);
        }
    }
}