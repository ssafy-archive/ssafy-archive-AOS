# ssafy-archive-AOS
```
app/src/main/java/com/ssafy/community/
├── MainActivity.kt                    // 앱 진입점 (스플래시 또는 라우팅 처리)
├── ui/
│   ├── splash/
│   │   └── SplashActivity.kt
│   ├── auth/
│   │   ├── LoginActivity.kt
│   │   └── RegisterActivity.kt
│   ├── group/
│   │   ├── GroupListActivity.kt
│   │   ├── CreateGroupActivity.kt
│   │   └── GroupMainActivity.kt
│   ├── profile/
│   │   ├── MyPageActivity.kt
│   │   └── ChangePasswordActivity.kt
│   ├── community/
│   │   ├── fragments/
│   │   │   ├── FreeBoardFragment.kt
│   │   │   ├── JobInfoFragment.kt
│   │   │   ├── GalleryFragment.kt
│   │   │   └── GroupMyPageFragment.kt
│   │   ├── PostDetailActivity.kt
│   │   ├── WritePostActivity.kt
│   │   └── BookmarkListActivity.kt
│   ├── components/           // 공통 컴포넌트
│   │   ├── CustomTextField.kt
│   │   ├── CustomButton.kt
│   │   └── LoadingDialog.kt
│   └── theme/
│       ├── Color.kt
│       ├── Type.kt
│       └── Theme.kt
├── data/
│   ├── model/
│   │   ├── User.kt
│   │   ├── Group.kt
│   │   └── Post.kt
│   ├── repository/
│   │   ├── UserRepository.kt
│   │   ├── GroupRepository.kt
│   │   └── PostRepository.kt
│   └── api/
│       └── ApiService.kt
├── utils/
│   ├── Constants.kt
│   ├── SharedPrefsManager.kt
│   └── Extensions.kt
└── viewmodel/
    ├── AuthViewModel.kt
    ├── GroupViewModel.kt
    └── PostViewModel.kt
```
