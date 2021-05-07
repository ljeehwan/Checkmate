from dataclasses import dataclass
from os import path, environ

base_dir = path.dirname(path.dirname(path.dirname(path.abspath(__file__))))

@dataclass
class Config:
    # 기본 Configuration
    BASE_DIR = base_dir

@dataclass
class LocalConfig(Config):
    # 로컬 환경설정
    PROJ_RELOAD: bool = True

    # Cross Origin설정
    ALLOW_SITE = ["*"]

@dataclass
class ProdConfig(Config):
    # 배포 환경설정
    PROJ_RELOAD: bool = False

    # Cross Origin 설정
    ALLOW_SITE = ["*"]

def conf():
    # 환경 불러오기
    config = dict(prod = ProdConfig(), local = LocalConfig())
    return config.get(environ.get("API_ENV", "local"))
