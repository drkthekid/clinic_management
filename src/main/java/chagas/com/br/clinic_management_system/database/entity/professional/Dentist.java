package chagas.com.br.clinic_management_system.database.entity.professional;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dentists")
@Getter
@Setter
public class Dentist extends Professional {
}
