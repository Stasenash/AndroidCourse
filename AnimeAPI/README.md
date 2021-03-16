# AnimeAPI AndroidStudioProject

Android project to fetch a list of popular anime (only available for now) and manga using JikanAPI.

[![Developer](https://img.shields.io/badge/chilicorn-sponsored-brightgreen.svg?logo=data%3Aimage%2Fpng%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAAA4AAAAPCAMAAADjyg5GAAABqlBMVEUAAAAzmTM3pEn%2FSTGhVSY4ZD43STdOXk5lSGAyhz41iz8xkz2HUCWFFhTFFRUzZDvbIB00Zzoyfj9zlHY0ZzmMfY0ydT0zjj92l3qjeR3dNSkoZp4ykEAzjT8ylUBlgj0yiT0ymECkwKjWqAyjuqcghpUykD%2BUQCKoQyAHb%2BgylkAyl0EynkEzmkA0mUA3mj86oUg7oUo8n0k%2FS%2Bw%2Fo0xBnE5BpU9Br0ZKo1ZLmFZOjEhesGljuzllqW50tH14aS14qm17mX9%2Bx4GAgUCEx02JySqOvpSXvI%2BYvp2orqmpzeGrQh%2Bsr6yssa2ttK6v0bKxMBy01bm4zLu5yry7yb29x77BzMPCxsLEzMXFxsXGx8fI3PLJ08vKysrKy8rL2s3MzczOH8LR0dHW19bX19fZ2dna2trc3Nzd3d3d3t3f39%2FgtZTg4ODi4uLj4%2BPlGxLl5eXm5ubnRzPn5%2Bfo6Ojp6enqfmzq6urr6%2Bvt7e3t7u3uDwvugwbu7u7v6Obv8fDz8%2FP09PT2igP29vb4%2BPj6y376%2Bu%2F7%2Bfv9%2Ff39%2Fv3%2BkAH%2FAwf%2FtwD%2F9wCyh1KfAAAAKXRSTlMABQ4VGykqLjVCTVNgdXuHj5Kaq62vt77ExNPX2%2Bju8vX6%2Bvr7%2FP7%2B%2FiiUMfUAAADTSURBVAjXBcFRTsIwHAfgX%2FtvOyjdYDUsRkFjTIwkPvjiOTyX9%2FAIJt7BF570BopEdHOOstHS%2BX0s439RGwnfuB5gSFOZAgDqjQOBivtGkCc7j%2B2e8XNzefWSu%2BsZUD1QfoTq0y6mZsUSvIkRoGYnHu6Yc63pDCjiSNE2kYLdCUAWVmK4zsxzO%2BQQFxNs5b479NHXopkbWX9U3PAwWAVSY%2FpZf1udQ7rfUpQ1CzurDPpwo16Ff2cMWjuFHX9qCV0Y0Ok4Jvh63IABUNnktl%2B6sgP%2BARIxSrT%2FMhLlAAAAAElFTkSuQmCC)](https://vk.com/akane_izanami)

## Содержане

#### [Jikan API описание и документация](#jikan-api)
#### [Стек технологий](#ts-desc)
#### [Структура проекта](#structure)


----------

### Jikan API описание и документация
<a name="jikan-api"></a>
[Jikan](https://jikan.moe/)
Jikan (時間) открытый исходный код PHP и REST API для «самого активного онлайн-сообщества и базы данных аниме + манги» - MyAnimeList.net. Он анализирует веб-сайт, чтобы предоставить API, которого нет у MyAnimeList.

Узнать больше [документация](https://jikan.docs.apiary.io/)

В данном проекте использовались GET запросы к API:
1) Получение аниме по индексу ```https://api.jikan.moe/v3/anime/{id}``` [Открыть пример запроса](https://api.jikan.moe/v3/anime/3)
2) Получение 20 самых популярных аниме ```https://api.jikan.moe/v3/search/anime?q=&order_by=members&sort=desc&limit=20``` [Открыть пример запроса](https://api.jikan.moe/v3/search/anime?q=&order_by=members&sort=desc&limit=20)

----------

### Стек технологий
<a name="ts-desc"></a>

 * Android/Kotlin
 * Jikan API
 * Room
 * Retrofit 2

----------

### Структура проекта
<a name="structure"></a>
```
AnimeAPI
├─ app
│  ├─ manifests
│  ├─ java
│  │  ├─ com.example.animeapi
│  │  │  └─ db
│  │  │  │  └─ AppDatabase
│  │  │  │  └─ UserDao
│  │  │  └─ models
│  │  │  │  └─ AnimeTitle
│  │  │  │  └─ AnimeTitleList
│  │  │  │  └─ User
│  │  │  └─ AnimeTitleAdapter
│  │  │  └─ ApiService
│  │  │  └─ FisrtFragment
│  │  │  └─ MainActivity
│  │  │  └─ SecondFragment
```

**AppDatabase** - подключение базы данных Room.

**UserDao** - интерфейс с описанием общих методов, которые будут использоваться при взаимодействии с БД.

**User** - объект БД


**AnimeTitle** - класс для получения одного аниме тайтла из запроса

**AnimeTitleList** - класс для получения списка аниме тайтлов из запроса

**AnimeTitleAdapter** - адаптер для подгрузки данных в RecyclerView

**ApiService** - сервис для api запросов


