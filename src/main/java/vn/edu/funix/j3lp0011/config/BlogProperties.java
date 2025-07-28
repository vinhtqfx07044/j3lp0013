package vn.edu.funix.j3lp0011.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "blog")
public class BlogProperties {
    
    private Posts posts = new Posts();
    
    public Posts getPosts() {
        return posts;
    }
    
    public void setPosts(Posts posts) {
        this.posts = posts;
    }
    
    public static class Posts {
        private int homepageCount = 5;
        
        public int getHomepageCount() {
            return homepageCount;
        }
        
        public void setHomepageCount(int homepageCount) {
            this.homepageCount = homepageCount;
        }
    }
}