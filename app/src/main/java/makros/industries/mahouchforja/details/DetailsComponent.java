package makros.industries.mahouchforja.details;

import dagger.Subcomponent;


@DetailsScope
@Subcomponent(modules = {DetailsModule.class})
public interface DetailsComponent {
    void inject(MovieDetailsFragment target);
}
