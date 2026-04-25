package chagas.com.br.clinic_management_system.database.entity.professional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Doctor extends Professional {
}
