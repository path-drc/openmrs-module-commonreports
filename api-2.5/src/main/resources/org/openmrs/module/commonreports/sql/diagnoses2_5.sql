select
    diagnosis_id,
    encounter_id,
    patient_id,
    diagnosis_coded,
    diagnosis_non_coded,
    diagnosis_coded_name,
    dx_rank,
    certainty,
    creator,
    date_created,
    voided,
    voided_by,
    date_voided,
    void_reason,
    uuid
from
    encounter_diagnosis
WHERE
    (:startDate IS NULL OR date_created >= :startDate)
    AND
    (:endDate IS NULL OR date_created <= :endDate)
