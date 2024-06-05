import 'dart:async';

import 'package:alcohol_chat/presentation/routes/route_path.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:go_router/go_router.dart';

//TODO : 로그인 수정 필요
class SplashPage extends StatefulWidget {
  const SplashPage({super.key});

  @override
  State<SplashPage> createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  @override
  void initState() {
    super.initState();
    Timer(Duration(seconds: 2),() => context.go(RoutePath.home));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: SvgPicture.asset("assets/svg/splash_main.svg"),
      ),
    );
  }
}
