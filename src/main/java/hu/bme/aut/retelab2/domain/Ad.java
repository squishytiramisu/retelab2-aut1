package hu.bme.aut.retelab2.domain;

import hu.bme.aut.retelab2.utils.SecretGenerator;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Ad {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private int price;

    @CreationTimestamp
    private Date creationDate;

    private String secret;

    public String tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    //LC3M9F
    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setSecret(String secret){
        this.secret = secret;
    }

    public String getSecret(){
        return this.secret;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
