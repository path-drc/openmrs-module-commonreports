select
    encounter.encounter_id AS encounter_id,
    encounter.patient_id AS encounter_patient_id,
    encounter.encounter_type AS encounter_type_id,
    encounter_type.name AS encounter_type_name,
    encounter_type.uuid AS encounter_type_uuid,
    encounter_type.retired AS encounter_type_retired,
    encounter.encounter_datetime AS encounter_datetime,
    encounter.voided AS encounter_voided,
    encounter.visit_id AS encounter_visit_id,
    visit_type.name AS visit_type_name,
    visit_type.uuid AS visit_type_uuid,
    visit_type.description AS visit_type_description,
    visit.date_started AS visit_date_started,
    visit.date_stopped AS visit_date_stopped,
    visit.voided AS visit_voided,
    visit_type.retired AS visit_type_retired,
    encounter.form_id AS encounter_form_id,
    form.name AS form_name,
    form.uuid AS form_uuid,
    form.version AS form_version,
    form.published AS form_published,
    form.encounter_type AS form_encounter_type,
    form.retired AS form_retired,
    encounter.location_id AS encounter_location_id,
    location.name AS location_name,
    location.uuid AS location_uuid,
    location.address1 AS location_address1,
    location.retired AS location_retired,
    encounter.creator AS encounter_creator,
    encounter.date_created AS encounter_date_created,
    encounter.voided_by AS encounter_voided_by,
    encounter.date_voided AS encounter_date_voided,
    encounter.void_reason AS encounter_void_reason,
    encounter.changed_by AS encounter_changed_by,
    encounter.date_changed AS encounter_date_changed,
    encounter.uuid AS encounter_uuid,
    encounter_type.description AS encounter_type_description,
    form.description AS form_description,
    form.template AS form_template,
    form.build AS form_build,

    location.description AS location_description,
    location.address2 AS location_address2,
    location.city_village AS location_city_village,
    location.state_province AS location_state_province,
    location.postal_code AS location_postal_code,
    location.country AS location_country,
    location.parent_location AS location_parent_location,
    location.county_district AS location_county_district
from
    encounter
    LEFT JOIN encounter_type encounter_type ON encounter.encounter_type = encounter_type.encounter_type_id
    LEFT JOIN location location ON encounter.location_id = location.location_id
    LEFT JOIN form form ON encounter.form_id = form.form_id
    LEFT JOIN visit visit ON encounter.visit_id = visit.visit_id
    LEFT JOIN visit_type visit_type ON visit.visit_type_id = visit_type.visit_type_id
WHERE
    encounter_datetime >= :startDate
    OR encounter_datetime <= :endDate