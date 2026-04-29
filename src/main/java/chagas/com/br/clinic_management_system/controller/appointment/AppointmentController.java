package chagas.com.br.clinic_management_system.controller.appointment;

import chagas.com.br.clinic_management_system.dto.request.AppointmentRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.AppointmentResponseDTO;
import chagas.com.br.clinic_management_system.service.appointment.create_appointment.CreateAppointmentService;
import chagas.com.br.clinic_management_system.service.appointment.list_appointments.ListAppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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


    @GetMapping
    public Page<AppointmentResponseDTO> findAllAppointments(@RequestParam int page,
                                                            @RequestParam int size) {
        return listAppointmentsService.listAllAppointments(page, size);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping("/{id}")
    public Page<AppointmentResponseDTO> findAllMyAppointments(@PathVariable UUID id,
                                                              @RequestParam int page,
                                                              @RequestParam int size) {
        return listAppointmentsService.listAllAppointmentsByPatient(id, page, size);
    }

    @PostMapping
    public AppointmentResponseDTO createExamAppointmentWithDoctor(@AuthenticationPrincipal UserDetails details,
                                                                  @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return createAppointmentService.createExamAppointmentWithDoctor(details.getUsername(), appointmentRequestDTO);
    }
}

