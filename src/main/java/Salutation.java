import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import javax.print.DocFlavor;
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

    @GET
    @Path("/form")
    @Produces("text/html")
    public String getForm(){
        return "<form action=\"/bonjour\" method=\"POST\">\n" +
                "\tChaine:  <input type=\"text\" name=\"nom\"><br>\n" +
                "\t<input type=\"submit\">\n" +
                "</form>";
     }

    @POST
    @Produces("text/plain")
    @Consumes("text/plain")
    public String postSalutation(@FormParam("nom") String nom){
        System.out.println("POST PLAIN");
        return "Ouech gros "+ nom + " bien ou bien ?\n";
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public String postSalutationForm(@FormParam("nom") String nom){
        System.out.println("POST PLAIN from form");
        return "Ouech gros "+ nom + " bien ou bien ? Alors on utilise des formulaires ?\n";
    }

    @POST
    @Produces("text/html")
    @Consumes("text/plain")
    public String postHTMLSalutation(@FormParam("nom") String nom) {
        System.out.println("POST HTML");
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
