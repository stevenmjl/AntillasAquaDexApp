package edu.ucne.antillasaquadexapp.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.antillasaquadexapp.data.local.dao.FavoritoDao
import edu.ucne.antillasaquadexapp.data.local.dao.EspecieDao
import edu.ucne.antillasaquadexapp.data.local.dao.UsuarioDao
import edu.ucne.antillasaquadexapp.data.local.database.AppDatabase
import edu.ucne.antillasaquadexapp.data.remote.EspecieApi
import edu.ucne.antillasaquadexapp.data.remote.ClimaApi
import edu.ucne.antillasaquadexapp.data.repository.EspecieRepositoryImpl
import edu.ucne.antillasaquadexapp.data.repository.UsuarioRepositoryImpl
import edu.ucne.antillasaquadexapp.data.repository.ClimaRepositoryImpl
import edu.ucne.antillasaquadexapp.domain.repository.EspecieRepository
import edu.ucne.antillasaquadexapp.domain.repository.UsuarioRepository
import edu.ucne.antillasaquadexapp.domain.repository.ClimaRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    @Named("EspecieRetrofit")
    fun provideEspecieRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://especiesmarinasapi.azurewebsites.net/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    @Named("ClimaRetrofit")
    fun provideClimaRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideEspecieApi(@Named("EspecieRetrofit") retrofit: Retrofit): EspecieApi =
        retrofit.create(EspecieApi::class.java)

    @Provides
    @Singleton
    fun provideClimaApi(@Named("ClimaRetrofit") retrofit: Retrofit): ClimaApi =
        retrofit.create(ClimaApi::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "antillas_aquadex.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUsuarioDao(db: AppDatabase): UsuarioDao = db.usuarioDao()

    @Provides
    fun provideFavoritoDao(db: AppDatabase): FavoritoDao = db.favoritoDao()

    @Provides
    fun provideEspecieDao(db: AppDatabase): EspecieDao = db.especieDao()

    @Provides
    @Singleton
    fun provideEspecieRepository(
        api: EspecieApi, 
        favoritoDao: FavoritoDao,
        especieDao: EspecieDao
    ): EspecieRepository = EspecieRepositoryImpl(api, favoritoDao, especieDao)

    @Provides
    @Singleton
    fun provideUsuarioRepository(usuarioDao: UsuarioDao): UsuarioRepository =
        UsuarioRepositoryImpl(usuarioDao)

    @Provides
    @Singleton
    fun provideClimaRepository(api: ClimaApi): ClimaRepository =
        ClimaRepositoryImpl(api)
}
