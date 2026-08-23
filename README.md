packages for clean architect 

├── presentation
│   └── login
│       ├── LoginActivity.kt
│       ├── LoginViewModel.kt
│       └── LoginUiState.kt
│
├── domain
│   ├── model
│   │   └── User.kt
│   │
│   ├── repository
│   │   └── AuthRepository.kt
│   │
│   └── usecase
│       └── LoginUseCase.kt
│
└── data
    └── repository
        └── AuthRepositoryImpl.kt


STEP 3 — Create :domain module
1. Current project
You should currently have:
│
└── app
└── src/main/java/com/company/enterprise/
│
├── presentation/
│   └── login/
│       ├── LoginActivity.kt
│       ├── LoginViewModel.kt
│       ├── LoginViewModelFactory.kt
│       └── LoginUiState.kt
│
├── domain/
│   ├── model/
│   │   └── User.kt
│   ├── repository/
│   │   └── AuthRepository.kt
│   └── usecase/
│       └── LoginUseCase.kt
│
└── data/
└── repository/
└── AuthRepositoryImpl.kt

After Step 3, we want:
EnterpriseMobilePlatform
│
├── app/
│
└── domain/
└── src/main/java/com/company/enterprise/domain/
├── model/
│   └── User.kt
├── repository/
│   └── AuthRepository.kt
└── usecase/
└── LoginUseCase.kt

The architecture becomes:
:app
│
┌────────────┼────────────┐
│            │            │
▼            ▼            ▼
presentation     data       :domain
│            ▲
└────────────┘
