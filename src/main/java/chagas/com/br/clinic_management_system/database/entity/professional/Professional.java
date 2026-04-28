package chagas.com.br.clinic_management_system.database.entity.professional;

import chagas.com.br.clinic_management_system.database.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "professionals")
// Ele cria uma tabela para a classe pai + uma tabela para cada filha, ligadas por ID.
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // false and true availability
    private Boolean availability = true;
}
