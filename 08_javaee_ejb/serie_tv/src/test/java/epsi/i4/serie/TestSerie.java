package epsi.i4.serie;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSerie {
	
	private EJBContainer ejbContainer;

	@Before
	public void startEJBContainer() throws Exception {
		ejbContainer = EJBContainer.createEJBContainer();
		ejbContainer.getContext().bind("inject", this);
	}

	@After
	public void closeEJBContainer() {
		ejbContainer.close();
	}
	
	@EJB
	private SerieStatelessEjb serieStatelessEjb;
	
	@Test
	public void canCreateSerie() throws Exception {
		Serie serie = new Serie();
		serie.setName("My Favorite Serie");
		serie.setCreationYear(2010);
		
		serieStatelessEjb.create(serie);
		
		assertNotNull(serie.getId());
		assertNotNull(serieStatelessEjb.get(serie.getId()));
	}

	@Test
	public void canDeleteSerie() throws Exception {
		Serie serie = new Serie();
		serie.setName("My Favorite Serie");
		serie.setCreationYear(2010);
		
		serieStatelessEjb.create(serie);
		serieStatelessEjb.delete(serie);
		
		assertNull(serieStatelessEjb.get(serie.getId()));
	}
}
