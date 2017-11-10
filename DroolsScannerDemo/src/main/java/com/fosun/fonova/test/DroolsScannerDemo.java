package com.fosun.fonova.test;

import java.util.Scanner;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsScannerDemo {

    public static void main(String[] args) {
        String groupId = "com.fosun.fonova.test";
        String artifactId = "DroolsKjarDemo";
        String version = "1.0.0";

        KieServices ks = KieServices.Factory.get();
        ReleaseId releaseId = ks.newReleaseId(groupId, artifactId, version);
        KieContainer kcontainer = ks.newKieContainer(releaseId);
        KieScanner kscanner = ks.newKieScanner(kcontainer);

        // Start the KieScanner polling the Maven repository every 10 seconds
        kscanner.start( 10000L );

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("load rule");
                KieSession ksession = kcontainer.newKieSession("ksession-rules");
                System.out.println("input a number to be checked by the rule");
                int num = scanner.nextInt();
                System.out.println("number input:" + num);
                TestNumber number = new TestNumber();
                number.number = num;
                ksession.insert(number);
                ksession.fireAllRules();
                ksession.dispose();
            }catch (Exception e) {
                scanner.close();
                return;
            }
        }
    }
}
