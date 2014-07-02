import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {
    private Contacto contacto;

    @PostConstruct
    public void init() {
        contacto = new Contacto();
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public void guardar(ActionEvent event) {
        EntityManager em;
        EntityManagerFactory emf = 
                Persistence.createEntityManagerFactory("AgendaPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(contacto);
        em.getTransaction().commit();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Contacto agregado con exito!!", null));
    }
}
