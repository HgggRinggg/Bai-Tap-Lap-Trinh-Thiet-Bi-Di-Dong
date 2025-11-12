# Bai-Tap-Lap-Trinh-Thiet-Bi-Di-Dong

# Eat-Clean-App---Mobile-dev

## 📁 Cấu trúc thư mục

```
src/main/java/com/team/eatcleanapp/
│
├── MainActivity.kt
│
├── data/
│   ├── local/
│   │   ├── AppDatabase.kt
│   │   ├── dao/
│   │   │   ├── UserDao.kt
│   │   │   ├── MealDao.kt
│   │   │   ├── FavoriteDao.kt
│   │   │   ├── DailyMenuDao.kt
│   │   │   └── MealIntakeDao.kt
│   │   └── entities/
│   │       ├── UserEntity.kt
│   │       ├── MealEntity.kt
│   │       ├── FavoriteEntity.kt
│   │       ├── DailyMenuEntity.kt
│   │       └── MealIntakeEntity.kt
│   │
│   ├── remote/
│   │   ├── ApiService.kt
│   │   └── dto/
│   │       └── MealDto.kt
│   │
│   └── repository/
│       ├── UserRepositoryImpl.kt
│       ├── MealRepositoryImpl.kt
│       ├── FavoriteRepositoryImpl.kt
│       └── DailyMenuRepositoryImpl.kt
│
├── domain/
│   ├── model/
│   │   ├── User.kt
│   │   ├── Meal.kt
│   │   ├── DailyMenu.kt
│   │   ├── MealIntake.kt
│   │   ├── NutritionInfo.kt
│   │   └── HealthMetrics.kt
│   │
│   ├── repository/
│   │   ├── UserRepository.kt
│   │   ├── MealRepository.kt
│   │   ├── FavoriteRepository.kt
│   │   └── DailyMenuRepository.kt
│   │
│   └── usecase/
│       ├── auth/
│       │   ├── LoginUseCase.kt
│       │   ├── RegisterUseCase.kt
│       │   ├── LogoutUseCase.kt
│       │   └── DeleteAccountUseCase.kt
│       │
│       ├── health/
│       │   ├── CalculateBmiUseCase.kt
│       │   ├── CalculateBmrUseCase.kt
│       │   ├── CalculateTdeeUseCase.kt
│       │   └── UpdateHealthMetricsUseCase.kt
│       │
│       ├── meal/
│       │   ├── GetAllMealsUseCase.kt
│       │   ├── SearchMealsUseCase.kt
│       │   ├── GetMealDetailUseCase.kt
│       │   └── AddMealToMenuUseCase.kt
│       │
│       ├── favorite/
│       │   ├── AddToFavoriteUseCase.kt
│       │   ├── RemoveFromFavoriteUseCase.kt
│       │   └── GetFavoriteMealsUseCase.kt
│       │
│       └── dailymenu/
│           ├── GetDailyMenuUseCase.kt
│           ├── GetWeeklyMenuUseCase.kt
│           ├── AddMealsToDayUseCase.kt
│           ├── DeleteDayMenuUseCase.kt
│           └── UpdateMealIntakeUseCase.kt
│
├── di/
│   ├── DatabaseModule.kt
│   ├── NetworkModule.kt
│   ├── RepositoryModule.kt
│   └── UseCaseModule.kt
│
├── ui/
│   ├── navigation/
│   │   ├── AppNavGraph.kt
│   │   ├── Screen.kt
│   │   └── NavigationRoute.kt
│   │
│   ├── components/
│   │   ├── TopBar.kt
│   │   ├── Sidebar.kt
│   │   ├── Gender.kt
│   │   ├── Age.kt
│   │   ├── InputWithUnit.kt
│   │   ├── MealCard.kt
│   │   ├── MealDetailCard.kt
│   │   ├── DatePickerPopup.kt
│   │   ├── AddMealPopup.kt
│   │   ├── SelectMealsPopup.kt
│   │   ├── NutritionProgressBar.kt
│   │   └── HealthMetricsDialog.kt
│   │
│   ├── screens/
│   │   ├── splash/
│   │   │   └── SplashScreen.kt
│   │   │
│   │   ├── auth/
│   │   │   ├── WelcomeScreen.kt
│   │   │   ├── LoginScreen.kt
│   │   │   └── RegisterScreen.kt
│   │   │
│   │   ├── onboarding/
│   │   │   ├── HealthCalculatorScreen.kt
│   │   │   ├── BmiScreen.kt
│   │   │   ├── BmrTdeeScreen.kt
│   │   │   └── GoalScreen.kt
│   │   │
│   │   ├── home/
│   │   │   └── HomeScreen.kt
│   │   │
│   │   ├── menu/
│   │   │   ├── MenuScreen.kt
│   │   │   └── MealDetailScreen.kt
│   │   │
│   │   ├── favorites/
│   │   │   └── FavoritesScreen.kt
│   │   │
│   │   ├── dailymenu/
│   │   │   └── DailyMenuScreen.kt
│   │   │
│   │   ├── profile/
│   │   │   └── ProfileScreen.kt
│   │   │
│   │   └── settings/
│   │       └── SettingsScreen.kt
│   │
│   ├── viewmodel/
│   │   ├── SplashViewModel.kt
│   │   ├── AuthViewModel.kt
│   │   ├── OnboardingViewModel.kt
│   │   ├── HomeViewModel.kt
│   │   ├── MenuViewModel.kt
│   │   ├── FavoritesViewModel.kt
│   │   ├── DailyMenuViewModel.kt
│   │   ├── ProfileViewModel.kt
│   │   └── SettingsViewModel.kt
│   │
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
└── util/
    ├── Constants.kt
    ├── DateUtils.kt
    ├── NutritionCalculator.kt
    └── Result.kt

```

- Sử dụng **camelCase** cho Biến và hàm
- Tên phải rõ ràng, mô tả đúng mục đích

- Sử dụng **PascalCase** cho Class và Interface

- Sử dụng **UPPER_SNAKE_CASE** cho Constants

- Mở ngoặc `{` trên cùng dòng với khai báo
- Đóng ngoặc `}` ở dòng riêng

- **Sau dấu phẩy**: Có 1 khoảng trắng
- **Xung quanh toán tử**: Có 1 khoảng trắng ở cả 2 bên

- **Comment 1 dòng** phía trên khai báo hàm có logic phức tạp hoặc quan trọng
