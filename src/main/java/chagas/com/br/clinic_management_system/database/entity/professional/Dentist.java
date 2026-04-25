package chagas.com.br.clinic_management_system.database.entity.professional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "dentists")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Dentist extends Professional {
}
