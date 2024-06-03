import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: const Icon(Icons.menu),
          onPressed: () {},
        ),
        title: Text(
          "Alcohol Chat",
          style: GoogleFonts.blackHanSans(),
        ),
        actions: [
          Padding(
            padding: const EdgeInsets.only(right: 8.0),
            child: TextButton(
              child: Text("술집 보기"),
              onPressed: () {},
              style: TextButton.styleFrom(
                foregroundColor: Colors.black,
                textStyle: GoogleFonts.blackHanSans(),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(right: 8.0),
            child: ElevatedButton(
              child: const Text("로그인"),
              onPressed: () {},
              style: ElevatedButton.styleFrom(
                foregroundColor: Colors.white,
                textStyle: GoogleFonts.blackHanSans(),
                backgroundColor: Colors.black,
              ),
            ),
          ),
        ],
        bottom: PreferredSize(
          preferredSize: const Size.fromHeight(0.0),
          child: Container(
            color: Colors.grey, // 밑줄 색상
            height: 2.0, // 밑줄 두께
          ),
        ),
      ),
    );
  }
}