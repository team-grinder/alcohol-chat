import 'package:alcohol_chat/presentation/pages/home/main_screen.dart';
import 'package:alcohol_chat/presentation/routes/route_path.dart';
import 'package:go_router/go_router.dart';

import '../pages/splash_page/splash_page.dart';

final GoRouter router = GoRouter(routes: [
  GoRoute(
    path: RoutePath.splash,
    name: 'splash',
    builder: (context, state) => const SplashPage(),
  ),
  GoRoute(
    path: RoutePath.home,
    name: 'home',
    builder: (context, state) => const HomePage(),
  ),
], initialLocation: '/splash'
);
