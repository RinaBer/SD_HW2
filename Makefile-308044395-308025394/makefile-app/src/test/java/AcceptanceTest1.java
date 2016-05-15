import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cs.technion.ac.il.sd.ExternalCompiler;
import cs.technion.ac.il.sd.app.Makefile;
import cs.technion.ac.il.sd.app.MakefileModule;
import makefileImpl.MakefileImpl;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.File;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by rina.berlin on 5/12/2016.
 */
public class AcceptanceTest1 implements IAcceptanceTest {

    private final ExternalCompiler mock = injector.getInstance(ExternalCompiler.class);
    private Makefile make = new MakefileImpl(mock);

    @Override
    public void Run(String filename) {
        when(mock.wasModified("5")).thenReturn(true);
        make.processFile(new File(getClass().getResource(filename).getFile()));
        InOrder inOrder = Mockito.inOrder(mock);
        for (Integer i=5; i>0; i--){
            inOrder.verify(mock).compile(Integer.toString(i));
        }
        Mockito.verify(mock, times(5)).compile(anyString());
    }
}
