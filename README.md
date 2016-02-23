###Roadshow Feedback capture application###

> As a user I want to capture attendee feedback so that we know how to improve further roadshows.

> As a user I want to capture feedback on an iPad so that I can capture the data on the go.

> As a user I want to capture feedback without having to zoom in or rotate so that entering data is as painless as possible

####Technical Summary####

 * Mobile Web Application
 * Bootstrap, AngularJS
 * Java
 * MongoDB
 * OpenShift

This application is a basic example of capturing data in a form and persisting it to a Mongo database. The requirement is for the application to be mobile friendly (i.e. responsive) - that is why Bootstrap 3 was used. AngularJS is used for the UI and for its provided http services.

A REST webservice has been implemented with Jersey/Java to handle the form submission. Endpoints are defined as follows:

| URL    | Method |Description |
|--------|--------|----------|
| ``ws/feedback`` | ``POST``| Saves provided JSON data to Mongo|
| ``ws/feedback`` | ``GET`` | Retrieves all saved feedback (order Date descending)|
| ``ws/locations``| ``GET``| Not used. Retrieves all Locations (for dropdown). Values are currently hardcoded. Future enhancement would be to enable for dynamic options|
| ``ws/statuses``| ``GET``| Not used. Retrieves all Statuses (for dropdown). See above|

This application is built/deployed & hosted with Redhat OpenShift (http://www.openshift.com).

Notes:

 * No validation required
 * Blank fields will be saved
 * Send button is disabled during send to prevent multiple submissions
 * Reset button clears all fields

####Local Deployment###

Using OpenShift the developer should commit & push to a developer instance to test changes. However in some circumstances this is not efficient. For local deployment docker is required.

To build/install run ``build.sh`` which runs the following:

 * ``mvn -Pdocker clean install docker:build``
 * ``docker-compose up``
