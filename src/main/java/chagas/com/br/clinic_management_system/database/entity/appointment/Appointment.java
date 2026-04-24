package chagas.com.br.clinic_management_system.database.entity.appointment;

import chagas.com.br.clinic_management_system.database.entity.patient.Patient;
import chagas.com.br.clinic_management_system.database.entity.professional.Dentist;
import chagas.com.br.clinic_management_system.database.entity.professional.Doctor;
import chagas.com.br.clinic_management_system.database.entity.professional.Professional;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    // dentist || doctor
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime schedule;

    @Enumerated(EnumType.STRING)
    private Status status;
}
