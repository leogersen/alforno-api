package com.leogersen.alfornoapi.domain.restaurant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "menu_item")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MenuItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O campo nome não pode estar vazio")
    @Size(max = 50)
    private String name;

    @NotEmpty(message = "O campo categoria não pode estar vazio")
    @Size(max = 25)
    private String category;

    @NotEmpty(message = "O campo descrição não pode estar vazio")
    @Size(max = 80)
    private String description;

    @Size(max = 50)
    private String image;

    //@UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo é inválido")
    private transient MultipartFile imageFile;

    @NotEmpty(message = "O campo descrição não pode estar vazio")
    @Min(0)
    private BigDecimal price;

    @NotNull
    private boolean highlight;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    /* public void setImageFileName() {
        if (getId() == null) {
            throw new IllegalStateException("O objeto precisa primeiro ser criado");
        }

        this.image = String.format("%04d-food.%s", getId(), FileType.of(imageFile.getContentType()).getExtension());
    } */
}
