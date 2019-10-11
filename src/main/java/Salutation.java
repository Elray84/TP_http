import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import javax.ws.rs.*;
import java.util.Scanner;

@Path("/bonjour")
public class Salutation {

    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getSalutation(@QueryParam("nom") String nom){
        return "Salutations mon cher " + nom + "\n";
    }

    @GET
    @Produces("text/html")
    @Consumes("text/plain")
    public String getHTMLSalutation(@QueryParam("nom") String nom) {
        return "<body><h1>Salutations !</h1>Alors " + nom + " on s'en va sans dire au revoir ?</body>"; }

    @POST
    @Produces("text/plain")
    @Consumes("text/plain")
    public String postSalutation(@FormParam("nom") String nom){
        return "Ouech gros "+ nom + " bien ou bien ?\n";
    }

    @POST
    @Produces("text/html")
    @Consumes("text/plain")
    public String postHTMLSalutation(@QueryParam("nom") String nom) {
        return "<body><h1>Salutations !</h1>Alors " + nom + " on s'en va sans dire au revoir ?</body>"; }

    public static void main(String[] args) {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(Salutation.class);
        sf.setResourceProvider(
                Salutation.class,
                new SingletonResourceProvider(new Salutation())
        );
        sf.setAddress("http://localhost:9000/");
        sf.create();

        System.out.println("Saisir car+return pour stopper le serveur");
        new Scanner(System.in).next();

        System.out.println("Fin");
    }
}
