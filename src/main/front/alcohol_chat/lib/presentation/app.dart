import 'package:alcohol_chat/presentation/pages/home/main_screen.dart';
import 'package:flutter/material.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Alcohol Chat',
      home: const HomePage(),
    );
  }
}
