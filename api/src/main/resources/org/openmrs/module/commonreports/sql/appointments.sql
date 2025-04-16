SELECT
    patient_appointment.patient_appointment_id AS patient_appointment_id,
    patient_appointment.patient_id AS patient_id,
    patient_appointment.appointment_number AS appointment_number,
    patient_appointment.start_date_time AS start_date_time,
    patient_appointment.location_id AS location_id,
    patient_appointment.end_date_time AS end_date_time,
    patient_appointment.appointment_service_id AS appointment_service_id,
    patient_appointment.appointment_service_type_id AS appointment_service_type_id,
    patient_appointment.status AS status,
    patient_appointment.appointment_kind AS appointment_kind,
    patient_appointment.comments AS comments,
    patient_appointment.related_appointment_id AS related_appointment_id,
    patient_appointment.creator AS creator,
    patient_appointment.date_created AS date_created,
    patient_appointment.changed_by AS changed_by,
    patient_appointment.date_changed AS date_changed,
    patient_appointment.voided AS voided,
    patient_appointment.voided_by AS voided_by,
    patient_appointment.date_voided AS date_voided,
    patient_appointment.void_reason AS void_reason,
    patient_appointment.uuid as uuid,
    appointment_service.name AS appointment_service_name,
    appointment_service.description AS appointment_service_description,
    appointment_service.voided AS appointment_service_voided,
    appointment_service.uuid AS appointment_service_uuid,
    appointment_service.color AS appointment_service_color,
    appointment_service.start_time AS appointment_service_start_time,
    appointment_service.end_time AS appointment_service_end_time,
    appointment_service.speciality_id AS appointment_service_speciality_id,
    appointment_service.max_appointments_limit AS appointment_service_max_appointments_limit,
    appointment_service.duration_mins AS appointment_service_duration_mins,
    appointment_service.initial_appointment_status AS appointment_service_initial_appointment_status,
    appointment_service_type.name AS appointment_service_type_name,
    appointment_service_type.duration_mins AS appointment_service_type_duration_mins,
    appointment_service_type.voided AS appointment_service_type_voided,
    appointment_service_type.uuid AS appointment_service_type_uuid,
    patient_appointment_provider.provider_id AS patient_appointment_provider,
    patient_appointment_provider.response AS patient_appointment_provider_response
FROM
    patient_appointment
    LEFT JOIN appointment_service appointment_service ON patient_appointment.appointment_service_id = appointment_service.appointment_service_id
    LEFT JOIN appointment_service_type appointment_service_type ON appointment_service_type.appointment_service_id = appointment_service.appointment_service_id
    LEFT JOIN patient_appointment_provider patient_appointment_provider ON patient_appointment_provider.patient_appointment_id = patient_appointment.patient_appointment_id
WHERE
    start_date_time >= :startDateTime