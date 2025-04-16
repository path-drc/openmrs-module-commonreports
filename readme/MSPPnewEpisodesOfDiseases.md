## New Episodes of Diseases
This report searches through a mix of chief complaints and diagnoses. It is a MSPP Report requested by the Haitian MoH to any clinic in Haiti.

**Setup**

It is setup by setting the following properties in [initializer](https://github.com/mekomsolutions/openmrs-module-initializer) `jsonkeyvalues`' domain configuration.

```bash
{
    ...
    ...,
    "report.MSPP.newEpisodesOfDiseases.active" : "true",
    "report.MSPP.newEpisodesOfDiseases.diagnosisList.conceptSet" : "uuid-to-diagnosis-list-concept-set",
    "report.MSPP.newEpisodesOfDiseases.questions.conceptSet" : "uuid-to-concept-set-containing-questions",
    "report.MSPP.newEpisodesOfDiseases.allDiagnoses.conceptSet" : "uuid-to-concept-set-containing-allDiagnoses",
    "report.MSPP.newEpisodesOfDiseases.referral.concept" : "uuid-for-referral-questionConcept-or-obsGroupingConcept"
}
```
`report.MSPP.newEpisodesOfDiseases.active` activates the report to be usable when the module is loaded.

`report.MSPP.newEpisodesOfDiseases.diagnosisList.conceptSet` specifies the diagnoses and/or chief complaints to filter from. These appear as separate rows for each on the report.MSPP.

`report.MSPP.newEpisodesOfDiseases.questions.conceptSet` specifies a concept set to the question concepts for which the recorded answer observations are being reported on.

`report.MSPP.newEpisodesOfDiseases.allDiseases.conceptSet` specifies a concept set containing all diseases, from which those diseases not specified in the disease list are aggregated in the report as "All Other Diagnoses" on one row. Else it can be a super set of sets of diagnoses, meaning it could contain sets where each has member diagnoses.

`report.MSPP.newEpisodesOfDiseases.referral.concept` specifies a concept for reporting on referred patients. It could be a coded question which when answered means the patient has been referred or concept that defines a group of observations capturing referral data.

Find the report template at [MSPP: New Episodes of Diseases](https://docs.google.com/spreadsheets/d/13A3gBRwi45-YwnArNsDgQB4EPVwsTswp/edit#gid=704979704)
