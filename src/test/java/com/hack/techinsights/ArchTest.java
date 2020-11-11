package com.hack.techinsights;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.hack.techinsights");

        noClasses()
            .that()
            .resideInAnyPackage("com.hack.techinsights.service..")
            .or()
            .resideInAnyPackage("com.hack.techinsights.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.hack.techinsights.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
