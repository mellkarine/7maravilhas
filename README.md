Maravilhas do Mundo - Projeto Android
Este é um aplicativo Android desenvolvido em Kotlin que exibe as Maravilhas do Mundo em um mapa, permite o login do usuário, e mantém um histórico de navegação usando Room Database. Ele utiliza Jetpack Compose para a interface moderna e o Google Maps SDK para visualização dos pontos turísticos.

Funcionalidades
Exibição de Mapa: Utiliza o Google Maps SDK para mostrar as Maravilhas do Mundo em um mapa.
Tela de Login: Uma interface simples para autenticação do usuário.
Histórico de Navegação: Salva e exibe um histórico de locais visitados usando Room Database.
Permissões de Localização: Solicita permissões para acessar a localização do usuário e centraliza o mapa na posição atual.
Tecnologias Utilizadas
Kotlin: Linguagem principal do desenvolvimento.
Jetpack Compose: Framework moderno para construção de UI.
Google Maps SDK: Para exibição de mapas e marcadores.
Room Database: Para persistência de dados local.
Coroutines: Para operações assíncronas e gerenciamento de threads.
Estrutura do Projeto
css
Copiar código
maravilhaslocalizacao/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/com/example/maravilhaslocalizacao/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── LoginActivity.kt
│   │   │   │   ├── AppDatabase.kt
│   │   │   │   ├── Historico.kt
│   │   │   │   └── HistoricoDao.kt
│   │   │   └── res/
│   │   │       ├── drawable/
│   │   │       ├── layout/
│   │   │       ├── mipmap/
│   │   │       └── values/
│   │   │           ├── colors.xml
│   │   │           ├── strings.xml
│   │   │           └── themes.xml
│   ├── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
Configuração do Projeto
Clone o repositório:

bash
Copiar código
git clone https://github.com/seu-usuario/maravilhaslocalizacao.git
Abra o projeto no Android Studio.

Certifique-se de que as dependências estão corretas no build.gradle.kts do módulo (app):

kotlin
Copiar código
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")

    // Google Maps e localização
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.2")

    // Room Database
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}
Sincronize o projeto com o Gradle: Clique em "Sync Now" para baixar todas as dependências.

Configure o Google Maps API Key:

Obtenha uma chave de API do Google Cloud Console.
Adicione a chave no arquivo AndroidManifest.xml:
xml
Copiar código
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="SUA_API_KEY_AQUI" />
Permissões
Certifique-se de que o aplicativo tem as permissões necessárias no AndroidManifest.xml:

xml
Copiar código
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
Executando o Aplicativo
Conecte um dispositivo Android ou inicie um emulador.
Execute o aplicativo pelo Android Studio.
Contribuindo
Faça um fork do projeto.
Crie uma branch para sua feature: git checkout -b minha-feature.
Commit suas alterações: git commit -m 'Adiciona minha feature'.
Faça um push para a branch: git push origin minha-feature.
Abra um Pull Request.
