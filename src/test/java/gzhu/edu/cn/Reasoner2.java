package gzhu.edu.cn;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.*;
import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program: exam
 * @description: 推理
 * @author: 丁国柱
 * @create: 2020-10-14 23:38
 */
public class Reasoner2 {

    /*public static void main(String[] args) throws OWLOntologyCreationException, FileNotFoundException {
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        try {
            ontModel.read(new FileInputStream("D:/owl/1.owl"), "");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        String rules = "[rule1: (?a <http://www.w3.org/2002/07/owl#sameAs> ?b) (?c <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?b) -> (?c <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?a)]";
       *//* OWLReasoner reasoner=new Reasoner.ReasonerFactory().createReasoner(o);

        reasoner.setDerivationLogging(false);
        InfModel inf = ModelFactory.createInfModel(reasoner, ontModel);
        //infgraph.setDerivationLogging(true);

        // 执行推理
        Property p = RDF.type;
        Selector selector =
                new com.sun.javafx.css.SimpleSelector(null, p, (RDFNode)null);
        StmtIterator stmtIterator = inf.listStatements(selector);
        while (stmtIterator.hasNext()) {
            Statement statement = stmtIterator.next();
            System.out.println(statement.getSubject()+","+statement.getPredicate()+","+statement.getObject());
        }*//*

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File("D:/owl/1.owl"));
        Configuration configuration = new Configuration();
        ReasonerFactory factory = new ReasonerFactory();
        configuration.throwInconsistentOntologyException = false;
        // The factory can now be used to obtain an instance of HermiT as an OWLReasoner.
        OWLReasoner reasoner = factory.createReasoner(ontology, configuration);
        List<InferredAxiomGenerator<? extends OWLAxiom>> generators = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
        generators.add(new InferredSubClassAxiomGenerator());
        generators.add(new InferredClassAssertionAxiomGenerator());
        // We dynamically overwrite the default disjoint classes generator since it tries to
        // encode the reasoning problem itself instead of using the appropriate methods in the
        // reasoner. That bypasses all our optimisations and means there is not progress report :-(
        // We don't want that!
        generators.add(new InferredDisjointClassesAxiomGenerator() {
            boolean precomputed = false;

            protected void addAxioms(OWLClass entity, OWLReasoner reasoner, OWLDataFactory dataFactory, Set<OWLDisjointClassesAxiom> result) {
                if (!precomputed) {
                    reasoner.precomputeInferences(InferenceType.DISJOINT_CLASSES);
                    precomputed = true;
                }
                for (OWLClass cls : reasoner.getDisjointClasses(entity).getFlattened()) {
                    result.add(dataFactory.getOWLDisjointClassesAxiom(entity, cls));
                }
            }

            InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner);

            List<InferredAxiomGenerator<?>> list = iog.getAxiomGenerators();
 for (InferredAxiomGenerator info : list) {
                System.out.println(info);
            }

        }
    }*/

    public static void main(String[] args) {

        int finalint = 1222;

        byte a = (byte) finalint;
        int b = finalint;
        int c = b % 256;
        if (c > 127) {
            int k = -(256 - c);
            System.out.println("第一种情况："+k);
        } else {
            System.out.println("第二种情况："+c);
        }
        System.out.println("final = " + a);
    }
}