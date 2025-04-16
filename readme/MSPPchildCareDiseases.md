## Child Care Report
This report searches children vitals, their result of visits and vaccinations on Vitamin-A and Albendazole. It is a MSPP Report requested by the Haitian MoH to any clinic in Haiti.

**Setup**

It is setup by setting the following properties in [initializer](https://github.com/mekomsolutions/openmrs-module-initializer) `jsonkeyvalues`' domain configuration. 

```bash
{
    ...
    ...,
    "report.MSPP.childCare.active" : "true",
    "report.MSPP.childCare.muacMeasurement.numericQuestion.concept" : "uuid-to-muac-measurement-numeric-concept",
    "report.MSPP.childCare.weightMeasurement.numericQuestion.concept" : "uuid-to-weight-measurement-numeric-concept",
    "report.MSPP.childCare.resultOfVisitQuestion.concept" : "uuit-to-results-of-visit-question-concept",
    "report.MSPP.childCare.malnutrition.visitType.uuid" : "uuid-to-malnutrition-visit-type",
    "report.MSPP.childCare.resultOfVisit.curedAnswer.concept" : "uuid-to-resultOfVisit-curedAnswer-concept",
    "report.MSPP.childCare.resultOfVisit.withdrawalAnswer.concept" : "uuid-to-resultOfVisit-withdrawalAnswer-concept",
    "report.MSPP.childCare.dose.numericQuestion.concept" : "uuid-to-dose-numericQuestion-concept",
    "report.MSPP.childCare.vaccinationsQuestion.concept" : "uuid-to-vaccinationsQuestion-concept",
    "report.MSPP.childCare.vitaminA.concept" : "uuid-to-vitaminA-concept",
    "report.MSPP.childCare.albendazole.concept" : "uuid-to-albendazole-concept",
    "report.MSPP.childCare.firstVisitQuestion.concept" : "uuid-to-firstVisit-question-concept",
    "report.MSPP.childCare.yesAnswer.concept" : "uuid-to-yesAnswer-concept"
}
```
`report.MSPP.childCare.active` activates the report to be usable when the module is loaded.

`report.MSPP.childCare.muacMeasurement.numericQuestion.concept` specifies MUAC measurement numeric concept. 

`report.MSPP.childCare.weightMeasurement.numericQuestion.concept` specifies weight measurement numeric concept

`report.MSPP.childCare.resultOfVisitQuestion.concept` specifies result of visit question concept

`report.MSPP.childCare.malnutrition.visitType.uuid` specifies malnutrition visit type.

`report.MSPP.childCare.resultOfVisit.curedAnswer.concept` specifies cured answer concept answering `report.MSPP.childCare.resultOfVisitQuestion.concept`

`report.MSPP.childCare.resultOfVisit.withdrawalAnswer.concept` specifies withdrawal answer concept answering `report.MSPP.childCare.resultOfVisitQuestion.concept` 

`report.MSPP.childCare.dose.numericQuestion.concept` specifies dosage measurement numeric concept

`report.MSPP.childCare.vaccinationsQuestion.concept`  specifies vaccination question concept

`report.MSPP.childCare.vitaminA.concept` specifies Vitamin A answer concept answering `report.MSPP.childCare.vaccinationsQuestion.concept`

`report.MSPP.childCare.albendazole.concept` specifies Albendazole answer concept answering `report.MSPP.childCare.vaccinationsQuestion.concept`

`report.MSPP.childCare.firstVisitQuestion.concept` specifies the concept required to determine whether the visit is the patients first visit.

`report.MSPP.childCare.yesAnswer.concept` specifies the yes concept answering `report.MSPP.childCare.firstVisitQuestion.concept`.

Find the report template at [MSPP Child Care Report](https://docs.google.com/spreadsheets/d/13A3gBRwi45-YwnArNsDgQB4EPVwsTswp/edit#gid=134070428).