package cs.technion.ac.il.sd.app;

import com.google.inject.AbstractModule;
import makefileImpl.MakefileImpl;

public class MakefileModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Makefile.class).to(MakefileImpl.class);
  }
}
