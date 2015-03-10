/*
 * Copyright 2015 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsoftware.eao;

import ar.com.gtsoftware.model.LegalGeneros;
import ar.com.gtsoftware.model.Personas;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@RunWith(Arquillian.class)
public class PersonasFacadeIT {

    private static final Logger LOG = Logger.getLogger(PersonasFacadeIT.class.getName());

    @EJB
    PersonasFacade facade;

    @Deployment
    public static JavaArchive createTestableDeployment() {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "ejb.jar")
                .addClasses(Personas.class, PersonasFacade.class)
                .addPackage(Personas.class.getPackage())
                .addClass(AbstractFacade.class)
                .addAsResource("glassfish/persistence-test.xml", "META-INF/persistence.xml") // Enable CDI
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        LOG.info(jar.toString(Formatters.VERBOSE));

        return jar;
    }

    @BeforeClass
    public void startUpClass() {
        //Init resources
    }

    @Test
    @InSequence(1)
    public void addPersona() {
        Personas p = new Personas();
        p.setActivo(true);
        p.setAltura("123");
        p.setApellidos("De los palotes");
        p.setCalle("Calle Falsa");
        p.setCliente(true);
        p.setDepto(null);
        p.setDocumento("123456");
        p.setEmail("p@p.com");
        p.setFechaAlta(new Date());
        p.setIdGenero(new LegalGeneros(1L));

        facade.createOrEdit(p);
    }

    @Test
    @InSequence(2)
    public void countTest() {

        int count = facade.count();
        Assert.assertEquals(1, count);
    }

}
