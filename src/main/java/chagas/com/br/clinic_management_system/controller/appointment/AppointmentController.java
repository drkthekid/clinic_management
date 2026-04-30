package chagas.com.br.clinic_management_system.controller.appointment;

import chagas.com.br.clinic_management_system.dto.request.AppointmentRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.AppointmentResponseDTO;
import chagas.com.br.clinic_management_system.service.appointment.conclude_appoitment.ConcludedAppointmentService;
import chagas.com.br.clinic_management_system.service.appointment.create_appointment.CreateAppointmentService;
import chagas.com.br.clinic_management_system.service.appointment.list_appointments.ListAppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final CreateAppointmentService createAppointmentService;
    private final ListAppointmentsService listAppointmentsService;
    private final ConcludedAppointmentService concludedAppointmentService;


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public Page<AppointmentResponseDTO> findAllAppointments(@RequestParam int page,
                                                            @RequestParam int size) {
        return listAppointmentsService.listAllAppointments(page, size);
    }


    @PreAuthorize("hasAuthority('ROLE_USER') AND #id == authentication.principal.id")
    @GetMapping("/patient/{id}")
    public Page<AppointmentResponseDTO> findAllMyAppointmentsPatient(@PathVariable UUID id,
                                                                     @RequestParam int page,
                                                                     @RequestParam int size) {
        return listAppointmentsService.listAllAppointmentsByPatient(id, page, size);
    }

    @PreAuthorize("hasAuthority('ROLE_STAFF') AND #id == authentication.principal.id ")
    @GetMapping("/professional/{id}")
    public Page<AppointmentResponseDTO> findAllMyAppointmentsProfessional(@PathVariable UUID id,
                                                                          @RequestParam int page,
                                                                          @RequestParam int size) {
        return listAppointmentsService.listAllAppointmentsByProfessional(id, page, size);
    }

    @PostMapping
    public AppointmentResponseDTO createExamAppointmentWithDoctor(@AuthenticationPrincipal UserDetails details,
                                                                  @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return createAppointmentService.createExamAppointmentWithDoctor(details.getUsername(), appointmentRequestDTO);
    }

    @PatchMapping("/{id}/{doctorId}/concluded")
    @ResponseStatus(HttpStatus.OK)
    public String concludedAppointment(@PathVariable Long id,
                                       @PathVariable UUID doctorId) {
        return concludedAppointmentService.concludedAppointment(id, doctorId);
    }
}

