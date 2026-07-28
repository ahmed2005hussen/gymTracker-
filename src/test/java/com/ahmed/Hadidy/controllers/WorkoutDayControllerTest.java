//package com.ahmed.Hadidy.controllers;
//
//import com.ahmed.Hadidy.entity.Profile;
//import com.ahmed.Hadidy.entity.User;
//import com.ahmed.Hadidy.entity.WorkoutDay;
//import com.ahmed.Hadidy.entity.WorkoutPlan;
//import com.ahmed.Hadidy.repository.UserRepository;
//import com.ahmed.Hadidy.repository.WorkoutDayRepository;
//import com.ahmed.Hadidy.repository.WorkoutPlanRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//class WorkoutDayControllerTest {
//
//    private static final long USER_ID = 1L;
//    private static final long PLAN_ID = 10L;
//    private static final long DAY_ID = 20L;
//
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private WorkoutPlanRepository workoutPlanRepository;
//    @Mock
//    private WorkoutDayRepository workoutDayRepository;
//    @InjectMocks
//    private WorkoutDayController controller;
//
//    private MockMvc mockMvc;
//    private User user;
//    private WorkoutPlan workoutPlan;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//        user = user(USER_ID, USER_ID);
//        workoutPlan = workoutPlan(PLAN_ID, USER_ID);
//        when(userRepository.findByUsername("ahmed")).thenReturn(Optional.of(user));
//    }
//
//    @Test
//    void createWorkoutDay_savesMappedDayAndReturnsCreated() throws Exception {
//        when(workoutPlanRepository.findById(PLAN_ID)).thenReturn(Optional.of(workoutPlan));
//        when(workoutDayRepository.save(any(WorkoutDay.class))).thenAnswer(invocation -> {
//            WorkoutDay saved = invocation.getArgument(0);
//            saved.setId(DAY_ID);
//            return saved;
//        });
//
//        mockMvc.perform(post("/api/workoutday/create")
//                        .principal(authentication())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {"workoutPlanId":10,"name":"Push day","description":"Chest and triceps",
//                                 "image":"push.png","expectedTime":45.0,"totalRepeat":36,"totalExercises":6}
//                                """))
//                .andExpect(status().isCreated())
//                .andExpect(content().string("Workout Day is created"));
//
//        ArgumentCaptor<WorkoutDay> dayCaptor = ArgumentCaptor.forClass(WorkoutDay.class);
//        verify(workoutDayRepository).save(dayCaptor.capture());
//        WorkoutDay savedDay = dayCaptor.getValue();
//        assertThat(savedDay.getWorkoutPlan()).isSameAs(workoutPlan);
//        assertThat(savedDay.getName()).isEqualTo("Push day");
//        assertThat(savedDay.getTotalExercises()).isEqualTo(6);
//    }
//
//    @Test
//    void listWorkoutDays_returnsDaysOwnedByAuthenticatedUser() throws Exception {
//        WorkoutDay day = workoutDay(DAY_ID, "Push day");
//        workoutPlan.setWorkoutDays(List.of(day));
//        when(workoutPlanRepository.findById(PLAN_ID)).thenReturn(Optional.of(workoutPlan));
//
//        mockMvc.perform(get("/api/workoutday/list/{planId}", PLAN_ID).principal(authentication()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("Push day"))
//                .andExpect(jsonPath("$[0].totalExercises").value(6));
//    }
//
//    @Test
//    void getWorkoutDay_returnsForbiddenWhenPlanBelongsToAnotherUser() throws Exception {
//        when(workoutPlanRepository.findById(PLAN_ID)).thenReturn(Optional.of(workoutPlan(PLAN_ID, 99L)));
//
//        mockMvc.perform(get("/api/workoutday/list/{planId}/{dayId}", PLAN_ID, DAY_ID)
//                        .principal(authentication()))
//                .andExpect(status().isForbidden())
//                .andExpect(content().string("Not Authorized"));
//    }
//
//    @Test
//    void editWorkoutDay_updatesOnlyFieldsProvidedInRequest() throws Exception {
//        WorkoutDay day = workoutDay(DAY_ID, "Old name");
//        day.setDescription("Existing description");
//        when(workoutPlanRepository.findById(PLAN_ID)).thenReturn(Optional.of(workoutPlan));
//        when(workoutDayRepository.findByWorkoutPlanId(PLAN_ID)).thenReturn(List.of(day));
//        when(workoutDayRepository.save(any(WorkoutDay.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        mockMvc.perform(patch("/api/workoutday/edit/{planId}/{dayId}", PLAN_ID, DAY_ID)
//                        .principal(authentication())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\":\"Updated name\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("workoutDay is Edited"));
//
//        assertThat(day.getName()).isEqualTo("Updated name");
//        assertThat(day.getDescription()).isEqualTo("Existing description");
//        verify(workoutDayRepository).save(day);
//    }
//
//    @Test
//    void deleteWorkoutDay_deletesDayThatBelongsToPlan() throws Exception {
//        WorkoutDay day = workoutDay(DAY_ID, "Push day");
//        when(workoutPlanRepository.findById(PLAN_ID)).thenReturn(Optional.of(workoutPlan));
//        when(workoutDayRepository.findByWorkoutPlanId(PLAN_ID)).thenReturn(List.of(day));
//        doNothing().when(workoutDayRepository).deleteById(DAY_ID);
//
//        mockMvc.perform(delete("/api/workoutday/delete/{planId}/{dayId}", PLAN_ID, DAY_ID)
//                        .principal(authentication()))
//                .andExpect(status().isOk())
//                .andExpect(content().string("was Deleted"));
//
//        verify(workoutDayRepository).deleteById(eq(DAY_ID));
//    }
//
//    private Authentication authentication() {
//        return new UsernamePasswordAuthenticationToken("ahmed", "not-used");
//    }
//
//    private User user(long userId, long profileId) {
//        User result = new User();
//        result.setId(userId);
//        Profile profile = new Profile();
//        profile.setId(profileId);
//        result.setProfile(profile);
//        return result;
//    }
//
//    private WorkoutPlan workoutPlan(long planId, long profileId) {
//        WorkoutPlan result = new WorkoutPlan();
//        result.setId(planId);
//        Profile profile = new Profile();
//        profile.setId(profileId);
//        result.setProfile(profile);
//        return result;
//    }
//
//    private WorkoutDay workoutDay(long id, String name) {
//        WorkoutDay result = new WorkoutDay();
//        result.setId(id);
//        result.setName(name);
//        result.setDescription("Chest and triceps");
//        result.setImage("push.png");
//        result.setExpectedTime(45.0);
//        result.setTotalRepeat(36);
//        result.setTotalExercises(6);
//        result.setWorkoutPlan(workoutPlan);
//        return result;
//    }
//}
