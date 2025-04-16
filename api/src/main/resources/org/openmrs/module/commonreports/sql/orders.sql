select
    orders.order_id AS order_id,
    orders.patient_id AS patient_id,
    orders.order_type_id AS order_type_id,
    order_type.name AS order_type_name,
    order_type.uuid AS order_type_uuid,
    order_type.java_class_name AS order_type_java_class_name,
    orders.concept_id AS concept_id,
    orders.orderer AS orderer,
    orders.encounter_id AS encounter_id,
    encounter.encounter_datetime AS encounter_datetime,
    encounter_type.name AS encounter_type_name,
    encounter_type.uuid AS encounter_type_uuid,
    orders.care_setting AS care_setting,
    care_setting.name AS care_setting_name,
    care_setting.care_setting_type AS care_setting_type,
    care_setting.uuid AS care_setting_uuid,
    orders.instructions AS instructions,
    orders.date_activated AS date_activated,
    orders.auto_expire_date AS auto_expire_date,
    orders.date_stopped AS date_stopped,
    orders.order_reason AS order_reason,
    orders.order_reason_non_coded AS order_reason_non_coded,
    orders.date_created AS date_created,
    orders.creator AS creator,
    orders.voided_by AS voided_by,
    orders.date_voided AS date_voided,
    orders.void_reason AS void_reason,
    orders.accession_number AS accession_number,
    orders.uuid AS uuid,
    orders.order_number AS order_number,
    orders.previous_order_id AS previous_order_id,
    orders.order_action AS order_action,
    orders.comment_to_fulfiller AS comment_to_fulfiller,
    orders.scheduled_date AS scheduled_date,
    orders.order_group_id AS order_group_id,
    orders.sort_weight AS sort_weight,
    encounter.voided AS encounter_voided,
    orders.voided AS voided,
    order_type.retired AS order_type_retired,
    encounter_type.retired AS encounter_type_retired,
    care_setting.retired AS care_setting_retired
from
    orders
    LEFT JOIN order_type order_type ON orders.order_type_id = order_type.order_type_id
    LEFT JOIN care_setting care_setting ON orders.care_setting = care_setting.care_setting_id
    LEFT JOIN encounter encounter ON encounter.encounter_id = orders.encounter_id
    LEFT JOIN encounter_type encounter_type ON encounter.encounter_type = encounter_type.encounter_type_id
WHERE
    date_activated >= :dateActivated
    AND (date_stopped IS NULL
              OR date_stopped <= :dateStopped)
