package makros.industries.mahouchforja;

import android.app.Application;
import android.os.StrictMode;

import io.realm.Realm;
import makros.industries.mahouchforja.details.DetailsComponent;
import makros.industries.mahouchforja.details.DetailsModule;
import makros.industries.mahouchforja.favorites.FavoritesModule;
import makros.industries.mahouchforja.listing.ListingComponent;
import makros.industries.mahouchforja.listing.ListingModule;
import makros.industries.mahouchforja.network.NetworkModule;

public class BaseApplication extends Application {
    private AppComponent appComponent;
    private DetailsComponent detailsComponent;
    private ListingComponent listingComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        initRealm();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .favoritesModule(new FavoritesModule())
                .build();
    }

    private void initRealm() {
        Realm.init(this);
    }

    public DetailsComponent createDetailsComponent() {
        detailsComponent = appComponent.plus(new DetailsModule());
        return detailsComponent;
    }

    public void releaseDetailsComponent() {
        detailsComponent = null;
    }

    public ListingComponent createListingComponent() {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public void releaseListingComponent() {
        listingComponent = null;
    }

    public ListingComponent getListingComponent() {
        return listingComponent;
    }
}
