# Controller Notes

Use this file to keep project notes grouped by controller. Add new notes directly below the relevant heading.

## Contents

- [DietPlanController](#dietplancontroller)
- [ExerciseController](#exercisecontroller)
- [MealController](#mealcontroller)
- [ProfileController](#profilecontroller)
- [SupplementController](#supplementcontroller)
- [UserController](#usercontroller)
- [WorkoutDayController](#workoutdaycontroller)
- [WorkoutPlanController](#workoutplancontroller)

---

## DietPlanController

<!-- Add DietPlanController notes here. -->

## ExerciseController

<!-- Add ExerciseController notes here. -->

## MealController

<!-- Add MealController notes here. -->

## ProfileController

<!-- Add ProfileController notes here. -->

## SupplementController

<!-- Add SupplementController notes here. -->

## UserController

### Improvement checklist

1. Finish `UserServiceImpl` and annotate it with `@Service`.
   - Implement `findByUsername` by calling `userRepository.findByUsername(username)`.
   - Add methods such as `registerUser(...)` and `changePassword(...)`.

2. Move business logic out of `UserController`.
   - The controller should receive the request, call `UserService`, and return the HTTP response.
   - Move password encoding, user lookup, duplicate checks, and saving users to `UserServiceImpl`.

3. Validate request data.
   - Add validation annotations to `UserRequest`, such as `@NotBlank` for the username and `@Size(min = 8)` for the password.
   - Add `@Valid` before `@RequestBody` in the controller methods.

4. Check whether a username already exists before registration.
   - If it exists, return `409 Conflict`.
   - Do not treat this as a server error.

5. Simplify successful registration.
   - If `userRepository.save(...)` succeeds, return `201 Created`.
   - Remove the `savedUser.getId() > 0` check.

6. Improve password-change error handling.
   - Return a clear client error when the old password is wrong.
   - Do not catch every exception inside the controller.
   - Use `@RestControllerAdvice` later to convert application exceptions into consistent error responses.

7. Do not return raw exception messages to API clients.
   - Log the full exception on the server.
   - Return a safe message such as `Unable to register user`.

8. Improve endpoint names when you refactor.
   - Use `POST /api/users/register`.
   - Use `PATCH /api/users/me/password` for the signed-in user's password.

9. Update the `User` entity.
   - Make the password column non-nullable.
   - Keep storing only encoded passwords; the current use of `PasswordEncoder` is correct.

10. Fix the enabled-user security check.
    - Ensure `HadidyUserDetailsService` passes `user.isEnabled()` to Spring Security's `UserDetails`.
    - Test that a disabled user cannot authenticate.

11. Add tests after the refactor.
    - Registering a new user returns `201 Created`.
    - A duplicate username returns `409 Conflict`.
    - A correct old password changes the password.
    - An incorrect old password does not change it.
    - A disabled user cannot log in.

## WorkoutDayController

<!-- Add WorkoutDayController notes here. -->

## WorkoutPlanController

<!-- Add WorkoutPlanController notes here. -->
