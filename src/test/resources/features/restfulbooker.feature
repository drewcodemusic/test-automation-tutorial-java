Feature: RESTful Booker API Testing

  @restfulbooker @API
  Scenario: Create and Update Booking
    Given I have an authentication token for "admin" and "password123"
    When I create a booking with firstname "Drew", lastname "Lin", totalprice 111, depositpaid "true", checkin "2025-03-08", checkout "2025-03-09", and additionalneeds "Breakfast"
    And I update the booking with firstname "Drew", lastname "Lin", totalprice 111, depositpaid "true", checkin "2025-03-08", checkout "2025-03-10", and additionalneeds "Breakfast"
    Then I should receive a successful restful-booker api response
    And The updated response jsonpath value "bookingdates.checkout" should be different from original response jsonpath value "booking.bookingdates.checkout"
    And The updated response jsonpath value "firstname" should be same as original response jsonpath value "booking.firstname"
