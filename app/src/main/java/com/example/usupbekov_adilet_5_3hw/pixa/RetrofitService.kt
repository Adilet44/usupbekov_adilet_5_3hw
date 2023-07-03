package com.example.usupbekov_adilet_5_3hw.pixa

class RetrofitService {
    private var retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    var api = retrofit.create(PixaBayApi::class.java)
}
}