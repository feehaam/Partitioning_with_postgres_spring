package playground.jpa.advanced.specification.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.jpa.advanced.specification.model.Photo;
import playground.jpa.advanced.specification.model.Product;
import playground.jpa.advanced.specification.model.Tag;
import playground.jpa.advanced.specification.model.Variant;
import playground.jpa.advanced.specification.repository.PhotoRepository;
import playground.jpa.advanced.specification.repository.ProductRepository;
import playground.jpa.advanced.specification.repository.TagRepository;
import playground.jpa.advanced.specification.repository.VariantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service @RequiredArgsConstructor
public class PopulateUtil {
    private final Random random = new Random();
    private final ProductRepository productsRepository;
    private final VariantRepository variantRepository;
    private final PhotoRepository photoRepository;
    private final TagRepository tagRepository;

    @PostConstruct
    protected void loadProducts(){
        clear();
        load();
    }

    private void load() {
        List<Product> generatedProducts = new ArrayList<>();
        for (int i=1; i<=10; i++) {
            generatedProducts.add(generateProduct(i));
        }
        productsRepository.saveAll(generatedProducts);
    }

    private void clear() {
        productsRepository.deleteAll();
        variantRepository.deleteAll();
        photoRepository.deleteAll();
        tagRepository.deleteAll();
    }

    private Product generateProduct(int si) {
        return Product.builder()
                .title("Sample product " + si)
                .description("This is the description of sample product " + si + ". "
                + "This description provides in detail information for this product.")
                .variants(generateVariant(si))
                .photos(generatePhotos(si))
                .tags(generateTags())
                .build();
    }

    private List<Variant> generateVariant(int si) {
        List<Variant> variants = new ArrayList<>();
        double price = random.nextInt(200) * 10;
        for (int i=0; i<random.nextInt(4) + 1; i++) {
            variants.add(
                    Variant.builder()
                            .name((i & 1) == 0 ? "P" + si + "V" + (i/2+1) : "GV" + (i/2+1))
                            .detail("This is the detail of the particular product variant.")
                            .price(price + random.nextInt((int) price / 10 + 10))
                            .availableQuantity(random.nextInt(100))
                            .isDefault(i == 0)
                            .build()
            );
        }
        return variants;
    }

    private List<Photo> generatePhotos(int si) {
        List<Photo> photos = new ArrayList<>();
        for (int i=0; i<random.nextInt(4) + 1; i++) {
            photos.add(
                    Photo.builder()
                            .url("http://drive.jpa-practice.com/product_" + si + "_photo_" + i + ".jpg")
                            .isThumbnail(i == 0)
                            .alternativeText("This is an alternative text for the particular photo")
                            .build()
            );
        }
        return photos;
    }

    private List<Tag> generateTags() {
        List<Tag> tags = new ArrayList<>();
        for (int i=0; i<random.nextInt(4) + 1; i++) {
            tags.add(
                    Tag.builder()
                            .name("TAG" + random.nextInt(10))
                            .build()
            );
        }
        return tags;
    }
}
