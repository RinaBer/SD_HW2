
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cs.technion.ac.il.sd.ExternalCompiler;
import cs.technion.ac.il.sd.app.Makefile;
import makefileImpl.MakefileImpl;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by rina.berlin on 5/14/2016.
 */
public class MakefileImplTest1 {
    Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            // will be replaced with a real implementation in staff tests
            bind(ExternalCompiler.class).toInstance(Mockito.mock(ExternalCompiler.class));
        }
    });
    private final ExternalCompiler mock = injector.getInstance(ExternalCompiler.class);
    private Makefile make = new MakefileImpl(mock);

    @Test
    public void acceptanceTest1() throws Exception {
        when(mock.wasModified("5")).thenReturn(true);
        make.processFile(new File(getClass().getResource("acceptance1.txt").getFile()));
        InOrder inOrder = Mockito.inOrder(mock);
        for (Integer i=5; i>0; i--){
            inOrder.verify(mock).compile(Integer.toString(i));
        }
        Mockito.verify(mock, times(5)).compile(anyString());
    }

}