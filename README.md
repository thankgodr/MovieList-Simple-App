# MovieList Simple App
Simple Android app that list movie from TMDB

## Architecture Used
- MVVM
- Clean Archtecture 
- Repository Pattern
- Dependency Injection


Used MVVM to utilized the power of viewmodels that fix errors and bugs caused by configuration changes.

Clean Architectureto make testing easy and adhere to SOLID principles. Also for scalabilty. each feature would have it own feature folder containing it own data, domain and presentation layer.

Libraries Used

- Glide : For images comes with image caching
- Compose Destination : Remove boilerplate codes from naviation graph.
- Hilt : Depency Injection 
- Retrofit
- Room

#Todos
- Write Test
- Implament load more when list get the buttom 
- Poulate more data omn the details Pages 
- Make share, like play buttons functional
- Localise all displayed text
- Fix all views to support accessbility 
