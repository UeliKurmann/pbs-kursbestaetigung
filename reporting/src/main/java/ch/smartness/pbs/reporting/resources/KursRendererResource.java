package ch.smartness.pbs.reporting.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import ch.smartness.pbs.reporting.uc.kursbestaetigung.KursDokumentGenerator;
import ch.smartness.pbs.reporting.uc.kursbestaetigung.KursParameter;
import ch.smartness.pbs.reporting.uc.kursbestaetigung.xml.Accessor;
import ch.smartness.pbs.reporting.uc.kursbestaetigung.xml.XMLKursConfig;
import ch.smartness.pbs.reporting.uc.kursbestaetigung.xml.XMLKursbeschreibung;

@Path("/kurs/renderer")
@Produces(MediaType.APPLICATION_JSON)
public class KursRendererResource {
    

    public KursRendererResource() {
       
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    @Timed
    public String ping() {
        return "pong";
    }
    
    @GET
    @Path("/demo")
    @Produces(MediaType.TEXT_HTML)
    @Timed
    public String demo() throws IOException{
    	return Resources.toString(KursRendererResource.class.getClassLoader().getResource("assets/demo.html"), Charsets.UTF_8);
    }
    
    @GET
    @Path("/demo/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public KursParameterJson getDemoParameter(){
    	return createDemoParameter();
    }
    
    @GET
    @Path("/demo/pdf/{kurs}/{lang}")
    @Produces("application/pdf")
    @Timed
    public byte[] getPdfDemo(@PathParam("kurs") String kurs, @PathParam("lang") String lang) throws Exception {
    	KursParameterJson parameter = createDemoParameter();
		
		
		return renderPdf(kurs, lang, parameter);
    }
    
    @POST
    @Path("/pdf/{kurs}/{lang}")
    @Produces("application/pdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    public byte[] getPdf(@PathParam("kurs") String kurs, @PathParam("lang") String lang, KursParameterJson kpj) throws Exception {
    	System.out.println(kpj);
    	
		return renderPdf(kurs, lang, kpj);
    }
    
    public byte[] renderPdf(String kurs, String lang, KursParameterJson kpj) throws Exception{
    	XMLKursConfig config = Accessor.readKursConfig(KursRendererResource.class.getClassLoader().getResourceAsStream("xml/kurs-config.xml"));
		String name = "xml/"+kurs+"_"+lang+".xml";
		XMLKursbeschreibung beschreibung = Accessor.readKursbeschreibung(KursRendererResource.class.getClassLoader().getResourceAsStream(name));
		
		KursParameter parameter = new KursParameter();
		
		parameter.setDauer(kpj.getDauer());
		parameter.setKursOrt(kpj.getKursOrt());
		parameter.setOrganisator(kpj.getOrganisator());

		parameter.setAnrede(kpj.getAnrede());
		parameter.setName(kpj.getName());
		parameter.setVorname(kpj.getVorname());
		parameter.setWohnort(kpj.getWohnort());
		parameter.setGeburtstag(kpj.getGeburtstag());
		
		return new KursDokumentGenerator().render(config, beschreibung, parameter).toByteArray();	
    }
    
    private KursParameterJson createDemoParameter() {
		KursParameterJson parameter = new KursParameterJson();
		
		parameter.setDauer("28.09.2017 - 29.09.2017");
		parameter.setKursOrt("Solothurn");
		parameter.setOrganisator("Pfadibewegung Schweiz (PBS)");

		parameter.setAnrede("Herr");
		parameter.setName("Rengel");
		parameter.setVorname("Heinrich");
		parameter.setWohnort("Oberdorf");
		parameter.setGeburtstag("09.08.1977");
		return parameter;
	}
}