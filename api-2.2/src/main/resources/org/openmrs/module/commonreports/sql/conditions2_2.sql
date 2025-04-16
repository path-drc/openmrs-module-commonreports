select
  `condition_id`,
  `additional_detail`,
  `previous_version`,
  `condition_coded`,
  `condition_non_coded`,
  `condition_coded_name`,
  `clinical_status`,
  `verification_status`,
  `onset_date`,
  `date_created`,
  `voided`,
  `date_voided`,
  `void_reason`,
  `uuid`,
  `creator`,
  `voided_by`,
  `changed_by`,
  `patient_id`,
  `end_date`
from
  conditions
where
    onset_date >= :onsetDate
    AND (
      end_date IS NULL
      OR end_date <= :endDate
    )