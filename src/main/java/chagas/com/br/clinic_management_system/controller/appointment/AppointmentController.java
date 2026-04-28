package chagas.com.br.clinic_management_system.controller.appointment;

import chagas.com.br.clinic_management_system.dto.request.AppointmentRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.AppointmentResponseDTO;
import chagas.com.br.clinic_management_system.service.appointment.create_appointment.CreateAppointmentService;
import chagas.com.br.clinic_management_system.service.appointment.list_appointments.ListAppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final CreateAppointmentService createAppointmentService;
    private final ListAppointmentsService listAppointmentsService;


    @GetMapping()
    public Page<AppointmentResponseDTO> findAllApointments(@RequestParam int page,
                                                           @RequestParam int size) {
        return listAppointmentsService.listAllAppointments(page, size);
    }

    @PostMapping
    public AppointmentResponseDTO createExamAppointmentWithDoctor(@AuthenticationPrincipal UserDetails details,
                                                                  @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return createAppointmentService.createExamAppointmentWithDoctor(details.getUsername(), appointmentRequestDTO);
    }
}

