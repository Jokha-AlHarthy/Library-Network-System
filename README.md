# Library Network System

A Spring Boot REST API for managing a multi-branch library network.

## Features

- Full CRUD operations for 12 entities
- DTO-based API responses with Builder pattern
- Bean Validation
- Soft Delete functionality
- Global Exception Handling
- Custom business operations for borrowing, returning, reservations, fines, and reviews
- Custom JPQL queries and statistics
- MySQL database integration
- Postman API testing

## Main Entities

Branch, Author, Category, Book, Member, Loan, Reservation, Fine, Review, Staff, Publisher, and Event.

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Lombok
- Jakarta Validation
- Postman

## API

The project provides REST endpoints for CRUD operations and library business processes such as:

- Borrow & return books
- Reserve unavailable books
- Pay and track fines
- Submit book reviews
- Search and statistics queries

## Project Requirements

All entities use a shared `BaseClass`, deleted records are handled using soft delete, and controllers return DTOs instead of raw entities. Validation and centralized exception handling are implemented throughout the API.

## Testing

All CRUD, business, validation, and error-handling endpoints are tested using Postman.