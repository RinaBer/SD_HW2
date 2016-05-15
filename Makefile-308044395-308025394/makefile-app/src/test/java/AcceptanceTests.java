import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cs.technion.ac.il.sd.ExternalCompiler;
import cs.technion.ac.il.sd.app.Makefile;
import makefileImpl.MakefileImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.File;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by akravi on 5/15/2016.
 */
public class AcceptanceTests {
    Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            // will be replaced with a real implementation in staff tests
            bind(ExternalCompiler.class).toInstance(Mockito.mock(ExternalCompiler.class));
        }
    });
    private final ExternalCompiler mock = injector.getInstance(ExternalCompiler.class);
    private Makefile make = new MakefileImpl(mock);

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5000000);

    @Test
    public void AcceptanceTest1(){
        when(mock.wasModified("5")).thenReturn(true);
        make.processFile(new File(getClass().getResource("acceptance1.txt").getFile()));
        InOrder inOrder = Mockito.inOrder(mock);
        for (Integer i=5; i>0; i--){
            inOrder.verify(mock).compile(Integer.toString(i));
        }
        Mockito.verify(mock, times(5)).compile(anyString());
    }

    @Test
    public void AcceptanceTest2(){
        when(mock.wasModified("f1")).thenReturn(true);
        when(mock.wasModified("f2")).thenReturn(false);
        make.processFile(new File(getClass().getResource("acceptance2.txt").getFile()));
        InOrder inOrder = Mockito.inOrder(mock);
        // verify right order of compilation
        inOrder.verify(mock).compile("f1");
        inOrder.verify(mock).compile("f2");
        inOrder.verify(mock).compile("t2");
        inOrder.verify(mock).compile("t3");
        inOrder.verify(mock).compile("main");
        Mockito.verify(mock, times(5)).compile(anyString());
    }

    @Test
    public void AcceptanceTest3(){
        when(mock.wasModified(anyString())).thenReturn(true);
        make.processFile(new File(getClass().getResource("acceptance3.txt").getFile()));
        InOrder inOrder = Mockito.inOrder(mock);
        Mockito.verify(mock, times(1000)).compile(anyString());
    }
}
